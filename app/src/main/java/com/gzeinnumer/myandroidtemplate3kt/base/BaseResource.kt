package com.gzeinnumer.myandroidtemplate3kt.base

data class BaseResource<T> private constructor(val status: BaseResourceStatus,
                                               val data: T?,
                                               val message: String?){
    enum class BaseResourceStatus {
        STATUS_1_SUCCESS,
        STATUS_2_ERROR,
        STATUS_3_EMPTY,
        STATUS_4_INFO,
        STATUS_5_LOGOUT,
        STATUS_6_LOADING
    }

    companion object {
        fun <T> success(msg: String,data: T): BaseResource<T> {
            return BaseResource(BaseResourceStatus.STATUS_1_SUCCESS, data, msg)
        }

        fun <T> error(msg: String): BaseResource<T> {
            return BaseResource(BaseResourceStatus.STATUS_2_ERROR, null, msg)
        }

        fun <T> empty(msg: String): BaseResource<T> {
            return BaseResource(BaseResourceStatus.STATUS_3_EMPTY, null, msg)
        }

        fun <T> info(msg: String): BaseResource<T> {
            return BaseResource(BaseResourceStatus.STATUS_4_INFO, null, msg)
        }

        fun <T> logout(): BaseResource<T> {
            return BaseResource(BaseResourceStatus.STATUS_5_LOGOUT, null, null)
        }

        fun <T> loading(): BaseResource<T> {
            return BaseResource(BaseResourceStatus.STATUS_6_LOADING, null, null)
        }
    }
}