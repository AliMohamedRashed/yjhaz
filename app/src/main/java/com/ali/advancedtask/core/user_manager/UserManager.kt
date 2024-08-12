package com.ali.advancedtask.core.user_manager

import com.ali.advancedtask.core.storge_manager.StorageHandler
import javax.inject.Inject


private const val USER_TOKEN_KEY = "user_token"
private const val USER_NAME_KEY = "user_name"

class UserManager @Inject constructor(private val storageHandler: StorageHandler) : UserHandler {

    override fun setUserToken(userId: String) = storageHandler.setString(USER_TOKEN_KEY, userId)

    override fun getUserToken() = storageHandler.getString(USER_TOKEN_KEY)

    override fun removeUserToken() = storageHandler.removeByKey(USER_TOKEN_KEY)

    override fun setUserName(userName: String) = storageHandler.setString(USER_NAME_KEY, userName)
    override fun getUserName() = storageHandler.getString(USER_NAME_KEY)
    override fun removeUserName() = storageHandler.removeByKey(USER_NAME_KEY)


}