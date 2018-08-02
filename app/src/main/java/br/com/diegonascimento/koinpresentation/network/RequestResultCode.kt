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
                200 -> Success()
                408 -> Timeout()
                304 -> NotModified()
                400 -> BadRequest()
                401 -> Unauthorized()
                404 -> NotFound()
                403 -> Forbidden()
                422 -> UnprocessableEntity()
                500 -> InternalServerError()
                405 -> MethodNotAllowed()
                998 -> NoConnectionAvailable()
                1001 -> JsonSyntaxResult()
                410 -> Gone()
                555 -> SslFail()
                else -> UnknownError()
            }
        }
    }


    class Success : RequestResultCode(errorCode = 200)
    class Timeout : RequestResultCode(errorCode = 408)
    class NotModified : RequestResultCode(errorCode = 304)
    class BadRequest : RequestResultCode(errorCode = 400)
    class Unauthorized : RequestResultCode(errorCode = 401)
    class NotFound : RequestResultCode(errorCode = 404)
    class Forbidden : RequestResultCode(errorCode = 403)
    class UnprocessableEntity : RequestResultCode(errorCode = 422)
    class InternalServerError : RequestResultCode(errorCode = 500)
    class MethodNotAllowed : RequestResultCode(errorCode = 405)
    class NoConnectionAvailable : RequestResultCode(errorCode = 998)
    class JsonSyntaxResult : RequestResultCode(errorCode = 1001)
    class Gone : RequestResultCode(errorCode = 410)
    class SslFail : RequestResultCode(errorCode = 555)
    class UnknownError : RequestResultCode(errorCode = 999)

}