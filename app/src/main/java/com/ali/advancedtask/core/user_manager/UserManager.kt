package com.ali.advancedtask.core.user_manager

import com.ali.advancedtask.core.storge_manager.StorageHandler
import javax.inject.Inject


private const val USER_ID_KEY = "user_id"
private const val USER_NAME_KEY = "user_name"
private const val CHECKBOX_STATE_KEY = "checkbox_stat"

class UserManager @Inject constructor(private val storageHandler: StorageHandler) : UserHandler {


    override fun setUserId(userId: String) = storageHandler.setString(USER_ID_KEY, userId)

    override fun getUserId() = storageHandler.getString(USER_ID_KEY)

    override fun removeUserId() = storageHandler.removeByKey(USER_ID_KEY)


    override fun setUserName(userName: String) = storageHandler.setString(USER_NAME_KEY, userName)
    override fun getUserName() = storageHandler.getString(USER_NAME_KEY)
    override fun removeUserName() = storageHandler.removeByKey(USER_NAME_KEY)


    override fun setCheckBoxState(isChecked: Boolean) =
        storageHandler.setBoolean(CHECKBOX_STATE_KEY, isChecked)

    override fun getCheckBoxState() = storageHandler.getBoolean(CHECKBOX_STATE_KEY)
    override fun removeCheckBoxState() = storageHandler.removeByKey(CHECKBOX_STATE_KEY)
}