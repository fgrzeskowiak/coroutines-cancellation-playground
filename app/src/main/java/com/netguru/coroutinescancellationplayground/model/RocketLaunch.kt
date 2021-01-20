package com.netguru.coroutinescancellationplayground.model

import com.netguru.coroutinescancellationplayground.common.UniqueId
import com.netguru.coroutinescancellationplayground.network.model.RocketLaunchResponse
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime

data class RocketLaunch(
    val missionName: String,
    val rocketId: String,
    val rocketName: String,
    val details: String?,
    val launchDateUTC: LocalDateTime,
    val successful: Boolean?
): UniqueId {
    override fun getUniqueId() = launchDateUTC.toInstant(ZoneOffset.UTC).epochSecond

    companion object {
        fun fromResponse(response: RocketLaunchResponse) = with(response) {
            RocketLaunch(
                missionName = missionName,
                rocketId = rocket.id,
                rocketName = rocket.name,
                details = details,
                launchDateUTC = ZonedDateTime.parse(launchDateUTC).toLocalDateTime(),
                successful = launchSuccess
            )
        }
    }
}
