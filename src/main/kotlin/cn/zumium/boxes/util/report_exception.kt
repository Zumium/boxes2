package cn.zumium.boxes.util

import cn.zumium.boxes.thrift.ServiceException

inline fun <T> reportException(op: String, doJob: () -> T): T {
    try {
        return doJob()
    }
    catch (e: Exception) {
        throw ServiceException(op, e.message)
    }
}