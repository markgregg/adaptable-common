package io.github.markgregg.common.api.endpoints

import io.github.markgregg.common.api.Request
import io.github.markgregg.common.api.Response
import io.github.markgregg.common.api.Rule
import io.github.markgregg.common.api.interfaces.ActiveTestCase
import io.github.markgregg.common.api.interfaces.EndPoint
import io.github.markgregg.common.api.interfaces.MessageConverter
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.locks.ReentrantReadWriteLock

abstract class BaseEndPoint(
    override val id: String,
    override val rules: List<Rule>,
    override val messageConverter: MessageConverter?
) : EndPoint {
    companion object {
        private val logger = LoggerFactory.getLogger(BaseEndPoint::class.java)
    }

    private val lock = ReentrantReadWriteLock()
    protected val activeTestCase = AtomicReference<ActiveTestCase?>()
    val unavailable = AtomicBoolean(false)

    /***
     *
     */
    override fun startTest(testCase: ActiveTestCase) {
        if( activeTestCase.get() != null ) {
            throw TestCaseAlreadyActiveException()
        }
        runWithWriteLock {
            activeTestCase.set(testCase)
        }
    }

    /***
     *
     */
    override fun endTest(id: String) {
        runWithWriteLock {
            if (activeTestCase.get() != null && activeTestCase.get()?.id == id) {
                activeTestCase.set(null)
            }
            unavailable.set(false)
        }
    }

    /**
     *
     */
    override fun processRequest(request: Request): Response? {

        val activeCase = fetchWithReadLock { activeTestCase.get() }
        if (activeCase?.rules != null) {
            val response = activeCase.rules.getResponse(request)
            if (response != null) {
                logger.info("Test ${activeCase.id} rule match, sending response")
                logger.debug("$response")
            }

            try {
                logger.debug("Notifying test of message")
                val clientResponse = activeTestCase.get()!!.messageHandler.invoke(request, response == null)
                if( clientResponse != null) {
                    return clientResponse
                }
            } catch (e: Exception) {
                logger.debug("Failed to send response to client, reason: ${e.message}")
            }
            return response
        }

        //check customisations
        val customResponse = handleRequest(request)
        if( customResponse != null ) {
            logger.info("Customisation rule match")
            logger.debug("$customResponse")
            return customResponse
        }

        for( rule in rules) {
            val ruleResponse = rule.evaluate(request)
            if( ruleResponse != null) {
                logger.info("Fallback rule match")
                logger.debug("$ruleResponse")
                return ruleResponse
            }
        }
        return null
    }

    override fun unavailable(id: String) {
        runWithReadLock {
            if (activeTestCase.get()?.id != id) {
                throw TestCaseNotActiveException()
            }
            unavailable.set(true)
            logger.info("End point is unavailable")
        }
    }

    override fun available(id: String) {
        runWithReadLock {
            if (activeTestCase.get()?.id != id) {
                throw TestCaseNotActiveException()
            }
            unavailable.set(false)
            logger.info("End point is available")
        }
    }

    protected abstract fun handleRequest(request: Request): Response?

    private fun List<Rule>.getResponse(request: Request): Response? {
        for( rule in this) {
            val ruleResponse = rule.evaluate(request)
            if( ruleResponse != null) {
                return ruleResponse
            }
        }
        return null
    }

    private fun runWithWriteLock( action: () -> Unit ) {
        lock.writeLock().lock()
        try {
            action.invoke()
        } finally {
            lock.writeLock().unlock()
        }
    }

    private fun runWithReadLock( action: () -> Unit ) {
        lock.readLock().lock()
        try {
            action.invoke()
        } finally {
            lock.readLock().unlock()
        }
    }

    private fun <T>fetchWithReadLock( action: () -> T ): T {
        lock.readLock().lock()
        try {
            return action.invoke()
        } finally {
            lock.readLock().unlock()
        }
    }

}