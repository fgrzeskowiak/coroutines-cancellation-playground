package com.netguru.coroutinescancellationplayground.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunchResponse(
    @SerialName("flight_number") val flightNumber: Int,
    @SerialName("mission_name") val missionName: String,
    @SerialName("launch_year") val launchYear: Int,
    @SerialName("launch_date_utc") val launchDateUTC: String,
    @SerialName("rocket") val rocket: RocketResponse,
    @SerialName("details") val details: String?,
    @SerialName("launch_success") val launchSuccess: Boolean?,
    @SerialName("links") val links: LinksResponse
)

@Serializable
data class RocketResponse(
    @SerialName("rocket_id") val id: String,
    @SerialName("rocket_name") val name: String,
    @SerialName("rocket_type") val type: String
)

@Serializable
data class LinksResponse(
    @SerialName("mission_patch") val missionPatchUrl: String?,
    @SerialName("article_link") val articleUrl: String?
)
