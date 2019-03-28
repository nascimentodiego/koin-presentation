/*
 * Copyright (C) 2018 Diego Figueredo do Nascimento.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.diegonascimento.koinpresentation.network


sealed class RequestResultCode(val errorCode: Int) {

    companion object {
        fun valueOf(errorCode: Int): RequestResultCode {
            return when (errorCode) {
                200 -> Success
                408 -> Timeout
                304 -> NotModified
                400 -> BadRequest
                401 -> Unauthorized
                404 -> NotFound
                403 -> Forbidden
                422 -> UnprocessableEntity
                500 -> InternalServerError
                405 -> MethodNotAllowed
                998 -> NoConnectionAvailable
                1001 -> JsonSyntaxResult
                410 -> Gone
                555 -> SslFail
                else -> UnknownError
            }
        }
    }


    object Success : RequestResultCode(errorCode = 200)
    object Timeout : RequestResultCode(errorCode = 408)
    object NotModified : RequestResultCode(errorCode = 304)
    object BadRequest : RequestResultCode(errorCode = 400)
    object Unauthorized : RequestResultCode(errorCode = 401)
    object NotFound : RequestResultCode(errorCode = 404)
    object Forbidden : RequestResultCode(errorCode = 403)
    object UnprocessableEntity : RequestResultCode(errorCode = 422)
    object InternalServerError : RequestResultCode(errorCode = 500)
    object MethodNotAllowed : RequestResultCode(errorCode = 405)
    object NoConnectionAvailable : RequestResultCode(errorCode = 998)
    object JsonSyntaxResult : RequestResultCode(errorCode = 1001)
    object Gone : RequestResultCode(errorCode = 410)
    object SslFail : RequestResultCode(errorCode = 555)
    object UnknownError : RequestResultCode(errorCode = 999)

}