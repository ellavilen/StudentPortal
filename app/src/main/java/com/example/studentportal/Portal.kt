import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal(
    var titleText: String,
    var linkText: String
) :Parcelable