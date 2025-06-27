package model

import component.MainComponent
import com.cemp.SharedRes.images as ImageRes
import com.cemp.SharedRes.strings as StringRes

object BottomBarItemsFactory {
    fun create(): List<BottomBarItem> {
        return listOf(
            BottomBarItem(
                name = StringRes.feature_main_matches_tab,
                icon = ImageRes.ic_cs,
                tab = MainComponent.Tab.Matches,
                childKClass = MainComponent.Child.Matches::class,
            ),
            BottomBarItem(
                name = StringRes.feature_main_teams_leaderboard_tab,
                icon = ImageRes.ic_leaderboard,
                tab = MainComponent.Tab.TeamsLeaderboard,
                childKClass = MainComponent.Child.TeamsLeaderboard::class,
            ),
        )
    }
}
