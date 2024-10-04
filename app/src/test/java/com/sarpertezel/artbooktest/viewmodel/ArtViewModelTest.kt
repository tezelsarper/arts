package com.sarpertezel.artbooktest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sarpertezel.artbooktest.MainCoroutineRule
import com.sarpertezel.artbooktest.getOrAwaitValueTest
import com.sarpertezel.artbooktest.repo.FakeArtRepository
import com.sarpertezel.artbooktest.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup()
    {
        viewModel = ArtViewModel(FakeArtRepository())

    }

    @Test
    fun `ìnsert art without year return error`()
    {
       viewModel.makeArt("Mona Lisa","Da Vinci","")
       val value = viewModel.insertArtMessage.getOrAwaitValueTest()
       assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `ìnsert art without name return error`()
    {
        viewModel.makeArt("","Da Vinci","1880")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `ìnsert art without artistName return error`()
    {
        viewModel.makeArt("Mona Lisa","","1880")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

}