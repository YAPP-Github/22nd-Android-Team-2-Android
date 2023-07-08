package com.yapp.bol.data.datasource.impl

import com.yapp.bol.data.model.group.MemberListResponse
import com.yapp.bol.data.model.group.response.GameApiResponse
import com.yapp.bol.data.model.group.response.MemberValidApiResponse
import com.yapp.bol.data.model.group.response.NewGroupApiResponse
import com.yapp.bol.data.model.group.response.ProfileUploadResponse
import com.yapp.bol.data.model.login.LoginResponse
import com.yapp.bol.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow
import java.io.File

interface RemoteDataSource {

    suspend fun login(type: String, token: String): LoginResponse?

    fun postFileUpload(
        file: File
    ): Flow<ApiResult<ProfileUploadResponse>>

    fun postCreateGroup(
        name: String,
        description: String,
        organization: String,
        profileImageUrl: String,
        nickname: String,
    ): Flow<ApiResult<NewGroupApiResponse>>

    fun getGameList(groupId: Int): Flow<ApiResult<GameApiResponse>>

    fun getValidateNickName(
        groupId: Int,
        nickname: String,
    ): Flow<ApiResult<MemberValidApiResponse>>

    fun getMemberList(
        groupId: Int,
        pageSize: Int,
        cursor: String? = null,
        nickname: String? = null,
    ): Flow<ApiResult<MemberListResponse>>

    suspend fun postGuestMember(groupId: Int, nickname: String)
}
