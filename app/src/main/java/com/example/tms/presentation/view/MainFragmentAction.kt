package com.example.tms.presentation.view

sealed class MainFragmentAction {

    object OpenLoginFragment: MainFragmentAction()

    object OpenSecondFragment: MainFragmentAction()

    object OpenNoteFragment: MainFragmentAction()
}