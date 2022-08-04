package com.soul.suhrob.soul.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.soul.suhrob.soul.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

fun FragmentActivity.statusBarColor(
    @ColorInt statusBarColorX: Int,
    @ColorInt navigationBarColorX: Int,
    darkStatusBarTint: Boolean
) {
    val win = window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        statusBarColor = statusBarColorX
        navigationBarColor = navigationBarColorX
    }

    val dec = win.decorView
    if (darkStatusBarTint) {
        dec.systemUiVisibility = dec.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        dec.systemUiVisibility = 0
    }
}

fun dipToPixels(context: Context, dipValue: Float): Float {
    val metrics: DisplayMetrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
}

fun changeMoneyType(sum: String): String {
    var reversedSum = ""
    for (i in sum.length - 1 downTo 0) {
        reversedSum += sum[i]
    }
    var changedSum = ""
    if (reversedSum.length > 3) {
        var k = 2
        for (i in reversedSum.indices) {
            changedSum += reversedSum[i]
            if (i >= k) {
                changedSum += " "
                k += 3
            }
        }
        reversedSum = ""
        for (i in changedSum.length - 1 downTo 0) {
            reversedSum += changedSum[i]
        }
    } else {
        reversedSum = sum
    }
    return reversedSum.trim()
}

fun getNavOptions(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()
}

fun View?.blockClickable(blockTimeMilles: Long = 500) {
    this?.isClickable = false
    Handler().postDelayed({ this?.isClickable = true }, blockTimeMilles)
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun spToPx(sp: Float, context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        context.resources.displayMetrics
    )
}

fun dismissKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (null != activity.currentFocus) imm.hideSoftInputFromWindow(
        activity.currentFocus!!
            .applicationWindowToken, 0
    )
}

fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun Fragment.goto(id: Int) {
    if (view == null) return
    Navigation.findNavController(view!!).navigate(id)
}

fun Fragment.goto(id: Int, bundle: Bundle) {
    if (view == null) return
    Navigation.findNavController(view!!).navigate(id, bundle)
}

fun View.gone(): View {
    visibility = View.GONE
    return this
}

fun View.invisible(): View {
    visibility = View.INVISIBLE
    return this
}

fun View.visible(): View {
    visibility = View.VISIBLE
    return this
}

fun View.enable(): View {
    isEnabled = true
    isClickable = true
    return this
}

fun View.disable(): View {
    isEnabled = false
    isClickable = false
    return this
}

//fun adjustFontScale(
//    configuration: Configuration?,
//    context: Context,
//    sharedPreferencesHelper: SharedPreferencesHelper
//) {
//    configuration?.let {
//        it.fontScale = 1F + sharedPreferencesHelper.getFontSize()
//        val metrics: DisplayMetrics = context.resources.displayMetrics
//        val wm: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        wm.defaultDisplay.getMetrics(metrics)
//        metrics.scaledDensity = configuration.fontScale * metrics.density
//        context.createConfigurationContext(it)
//        context.resources.displayMetrics.setTo(metrics)
//    }
//}

fun checkCardIsExist(ccNumber: String): Boolean {
    var sum = 0
    var alternate = false
    for (i in ccNumber.length - 1 downTo 0) {
        var n = ccNumber.substring(i, i + 1).toInt()
        if (alternate) {
            n *= 2
            if (n > 9) {
                n = n % 10 + 1
            }
        }
        sum += n
        alternate = !alternate
    }
    return sum % 10 == 0
}

fun getFormattedDateToString(date: Date): String =
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)

fun getParsingStringToDate(date: String): Date =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+mm:ss").parse(date)

fun getParsingStringToDateForMonitoring(date: String): Date =
    SimpleDateFormat("dd-MM-yyyy").parse(date)


fun AppCompatEditText.formatCurrency(
    @NonNull fallback: String, @NonNull textWatcher: TextWatcher,
    locale: Locale = Locale.US
) {

    removeTextChangedListener(textWatcher)

    var original = text.toString()
    if (original.startsWith(".")) {
        // If the user press on '.-' key on the beginning of the amount - we are getting '.' and we turn it into '-'
        setText(original.replaceFirst(".", "-"))
        addTextChangedListener(textWatcher)
        setSelection(text?.length ?: 0)
        return
    }
    val split = original.split(".")
    when (split.size) {
        0 -> {
            setText(fallback)
            addTextChangedListener(textWatcher)
            setSelection(text?.length ?: 0)
            return
        }
        1 -> {
            if (split[0] == "-") {
                setText("-")
                addTextChangedListener(textWatcher)
                setSelection(text?.length ?: 0)
                return
            }
        }
        2 -> {
            if (split[1].length > 2) {
                setText(fallback)
                addTextChangedListener(textWatcher)
                setSelection(text?.length ?: 0)
                return
            }
        }
    }
    // We store the decimal value in a local variable
    val decimalSplit = original.split(".")
    // flag to indicate that we have a decimal part on the original String.
    val hasDecimal = decimalSplit.size > 1
    if (hasDecimal) {
        original = decimalSplit[0]
    }
    val isNegative = original.startsWith("-")
    val cleanString: String = original.replace("""[$,]""".toRegex(), "")

    var result = if (cleanString.isNotEmpty() && cleanString != "-") {
        val formatString = original.replace("""[-$,.]""".toRegex(), "")
        // Add Commas and Currency symbol.
        var result = NumberFormat.getCurrencyInstance(locale).format(formatString.toDouble())
        result = result.split('.')[0]
        if (isNegative) {
            // If it was negative we must add the minus sign.
            result = "-${result}"
        }
        if (hasDecimal) {
            // after the formatting the decimal is omitted, we need to append it.
            result = "${result}.${decimalSplit[1]}"
        }
        result
    } else {
        original
    }
    result = if (result.length > 1) result.substring(1) else result
    setText(result)
    addTextChangedListener(textWatcher)
    setSelection(text?.length ?: 0)
}

fun convertPixelsToDp(resources: Resources, dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}