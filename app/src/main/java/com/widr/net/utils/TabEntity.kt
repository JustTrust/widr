package com.widr.net.utils

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by a.belichenko on 13.04.2019.
 * mail: a.belichenko@gmail.com
 */
class TabEntity(var title: String, var selectedIcon: Int, var unSelectedIcon: Int): CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}