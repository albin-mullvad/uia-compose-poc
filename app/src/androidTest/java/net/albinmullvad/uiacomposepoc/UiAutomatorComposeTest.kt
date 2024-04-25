package net.albinmullvad.uiacomposepoc

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiAutomatorComposeTest {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val device = UiDevice.getInstance(instrumentation)
    private val targetContext = instrumentation.targetContext

    @Test
    fun test() {
        launchAppAndWait()

        device.findObjectWithTimeout(By.text("Main screen says hello!"))
        device.findObjectWithTimeout(By.text("Open secondary")).click()
        device.findObjectWithTimeout(By.text("Back")).click()
        device.findObjectWithTimeout(By.text("Main screen says hello!"))
    }

    private fun UiDevice.findObjectWithTimeout(
        selector: BySelector,
        timeout: Long = 10000L
    ): UiObject2 {
        return if (wait(Until.hasObject(selector), timeout)) {
            findObject(selector)
        } else {
            throw IllegalArgumentException(
                "No matches for selector within timeout ($timeout): $selector"
            )
        }
    }

    private fun launchAppAndWait() {
        val targetPackageName = "net.albinmullvad.uiacomposepoc"
        val packageLaunchTimeout = 10000L

        device.pressHome()

        // Wait for launcher
        device.wait(
            Until.hasObject(By.pkg(device.launcherPackageName).depth(0)),
            packageLaunchTimeout
        )

        val intent =
            targetContext.packageManager.getLaunchIntentForPackage(targetPackageName)?.apply {
                // Clear out any previous instances
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        targetContext.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(targetPackageName).depth(0)), packageLaunchTimeout)
    }
}
