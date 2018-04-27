package com.domain.internprep

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class RobolectricTest {
    private lateinit var subject: MainActivity

    @Before
    fun setUp() {
        subject = Robolectric.buildActivity(MainActivity::class.java)
                .create().get()
    }

    @Test
    fun firstTest() {
        assertThat(12).isEqualTo(12)
    }
}