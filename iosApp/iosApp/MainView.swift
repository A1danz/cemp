//
//  MainView.swift
//  iosApp
//
//  Created by Нияз Ризванов on 25.06.2025.
//

import SwiftUI
import ComposeApp
import Foundation

struct MainView: View {
    let component: MainComponent
    @ObservedObject var stack: ObservableValue<ChildStack<AnyObject, MainComponentChild>>
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    
    init(component: MainComponent) {
        self.component = component
        self.stack = ObservableValue(component.childStack)
    }
    
    var body: some View {
        ZStack {
            // Check if we should show full screen views (details)
            switch stack.value.active.instance {
            case let matchDetails as MainComponentChild.MatchDetails:
                MatchDetailView(component: matchDetails.component)
            case let teamDetails as MainComponentChild.TeamDetails:
                TeamDetailView(component: teamDetails.component)
            default:
                // Show TabBar for main screens
                TabBarView(component: component, stack: stack)
            }
        }
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }

}

// TeamDetailsView is now in separate file TeamDetailView.swift

// MARK: - TabBar View
struct TabBarView: View {
    let component: MainComponent
    @ObservedObject var stack: ObservableValue<ChildStack<AnyObject, MainComponentChild>>
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    
    private var currentTab: TabType {
        switch stack.value.active.instance {
        case is MainComponentChild.Matches:
            return .matches
        case is MainComponentChild.TeamsLeaderboard:
            return .worldRanking
        default:
            return .matches
        }
    }
    
    var body: some View {
        VStack(spacing: 0) {
            // Content
            Group {
                switch stack.value.active.instance {
                case let matches as MainComponentChild.Matches:
                    MatchesView(component: matches.component)
                case let teamsLeaderboard as MainComponentChild.TeamsLeaderboard:
                    WorldRankingView(component: teamsLeaderboard.component)
                                 default:
                     // Default to matches if unknown state
                     EmptyView()
                }
            }
            
            // TabBar
            VStack(spacing: 0) {
                HStack(spacing: 0) {
                    // Matches Tab
                    TabBarItem(
                        title: StringRes.feature_matches_title.desc().localized(),
                        iconResource: ImageRes.ic_cs,
                        isSelected: currentTab == .matches
                                     ) {
                         component.onTabSelected(tab: MainComponentTabMatches())
                     }
                     
                     // World Ranking Tab
                     TabBarItem(
                         title: StringRes.feature_teams_leaderboard_title.desc().localized(),
                         iconResource: ImageRes.ic_leaderboard,
                         isSelected: currentTab == .worldRanking
                     ) {
                         component.onTabSelected(tab: MainComponentTabTeamsLeaderboard())
                     }
                }
                .frame(height: 80)
                .overlay(
                    Rectangle()
                        .fill(theme.textColor.opacity(0.1))
                        .frame(height: 0.5),
                    alignment: .top
                )
                .padding(.bottom)
            }
            .background(theme.secondaryBackground)
            .ignoresSafeArea(edges: .bottom)
        }
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }
}

// MARK: - TabBar Item
struct TabBarItem: View {
    let title: String
    let iconResource: ComposeApp.ImageResource
    let isSelected: Bool
    let action: () -> Void
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Button(action: action) {
            VStack(spacing: 4) {
                // Icon from moko-resources
                Image(uiImage: (iconResource.toUIImage() ?? UIImage()).withRenderingMode(.alwaysTemplate))
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 24, height: 24)
                    .foregroundColor(isSelected
                                     ? theme.isDarkMode == true ? .white : .black
                                     : theme.textColor.opacity(0.4)
                    )

                // Title
                Text(title)
                    .font(theme.text12Medium)
                    .foregroundColor(isSelected
                                     ? theme.isDarkMode == true ? .white : .black
                                     : theme.textColor.opacity(0.4)
                    )
                    .lineLimit(1)
                    .minimumScaleFactor(0.8)
            }
            .frame(maxWidth: .infinity)
        }
        .buttonStyle(PlainButtonStyle())
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }
}

// MARK: - Tab Type
enum TabType {
    case matches
    case worldRanking
}
