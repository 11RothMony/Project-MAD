package kh.edu.rupp.ite.projectmad.data.model



//data class UserProfileResponse(
////    val userProfile: UserProfile
//    val userProfile: ProfileUser
//
//)

//data class UserProfile(
//    val message: String?,
//    val status: String,
//    val data: ProfileUser,
//)

data class ProfileUser(
    val profile: ProfileShow
)

data class ProfileShow(
    val coverimage: String?,
    val firstname: String,
    val lastname: String,
    val profileimage: String?,
    val token: String?
)