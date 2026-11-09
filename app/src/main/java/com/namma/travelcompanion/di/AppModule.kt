package com.namma.travelcompanion.di

import com.namma.travelcompanion.core.network.di.networkModule
import com.namma.travelcompanion.data.di.coreDataModule
import com.namma.travelcompanion.feature.commute.di.commuteModule
import com.namma.travelcompanion.presentation.explore.di.exploreModule
import com.namma.travelcompanion.presentation.phrasebook.di.phrasebookModule
import com.namma.travelcompanion.presentation.plan.di.planModule
import com.namma.travelcompanion.presentation.safety.di.safetyModule

val appModules = listOf(
    networkModule,
    coreDataModule,
    commuteModule,
    exploreModule,
    phrasebookModule,
    planModule,
    safetyModule
)
