package br.com.douglasmotta.whitelabeltutorial.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class CoroutineTestCase {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
}