package com.example.multitasked   // 👈 IMPORTANT: root package

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CrowdTasksApp : Application()
