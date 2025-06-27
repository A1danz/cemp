//
//  WorldRankingView.swift
//  iosApp
//
//  Created by AI Assistant on 27.06.2025.
//

import SwiftUI
import ComposeApp
import Foundation

struct WorldRankingView: View {
    let component: TeamsLeaderboardComponent
    
    @StateValue
    private var model: TeamsLeaderboardComponentModel
    
    @Environment(\.colorScheme) var colorScheme
    @StateObject private var theme = CEMPTheme()
    
    init(component: TeamsLeaderboardComponent) {
        self.component = component
        _model = StateValue(component.state)
    }
    
    var body: some View {
        ZStack {
            theme.mainBackground
                .ignoresSafeArea()
            
            VStack(spacing: 0) {
                headerView
                
                if model.isLoading {
                    loadingView
                } else if model.isError {
                    errorView
                } else {
                    contentView
                }
            }
        }
        .navigationBarHidden(true)
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
    }
    
    // MARK: - Header View
    private var headerView: some View {
        VStack(spacing: 12) {
            HStack {
                Text(StringRes.feature_teams_leaderboard_title.desc().localized())
                    .font(theme.text28Bold)
                    .foregroundColor(theme.textColor)
                
                Spacer()
            }
            .padding(.horizontal, 16)
        }
        .padding(.top, 16)
    }
    
    // MARK: - Loading View
    private var loadingView: some View {
        VStack {
            Spacer()
            ProgressView()
                .scaleEffect(1.5)
                .progressViewStyle(CircularProgressViewStyle(tint: theme.blueText))
            
            Text(StringRes.feature_teams_leaderboard_loading.desc().localized())
                .font(theme.text16SemiBold)
                .foregroundColor(theme.textColor)
                .padding(.top, 16)
            Spacer()
        }
    }
    
    // MARK: - Error View
    private var errorView: some View {
        VStack(spacing: 16) {
            Spacer()
            
            Image(systemName: "exclamationmark.triangle")
                .font(.system(size: 48))
                .foregroundColor(.red)
            
            Text(StringRes.feature_teams_leaderboard_error_title.desc().localized())
                .font(theme.text20SemiBold)
                .foregroundColor(theme.textColor)
            
            Text(StringRes.feature_teams_leaderboard_error_message.desc().localized())
                .font(theme.text14Medium)
                .foregroundColor(theme.textColor.opacity(0.7))
                .multilineTextAlignment(.center)
            
            Spacer()
        }
        .padding()
    }
    
    // MARK: - Content View
    private var contentView: some View {
        ScrollView {
            VStack(spacing: 0) {
                ForEach(Array(model.teams.enumerated()), id: \.offset) { index, team in
                    RankingRowView(
                        position: index + 1,
                        team: team,
                        isLast: index == model.teams.count - 1
                                         ) {
                         component.onIntent(intent__: TeamsLeaderboardComponentIntentOnTeamClicked(teamId: Int32(team.id)))
                     }
                }
            }
            .background(theme.secondaryBackground)
            .cornerRadius(12)
            .padding(.horizontal, 16)
            .padding(.top, 24)
        }
    }
}

// MARK: - Ranking Row View
struct RankingRowView: View {
    let position: Int
    let team: TeamModel
    let isLast: Bool
    let onTap: () -> Void
    
    @Environment(\.colorScheme) var colorScheme
    @StateObject private var theme = CEMPTheme()
    
    var body: some View {
        Button(action: onTap) {
            HStack(spacing: 16) {
                Text("\(position)")
                    .font(theme.text16SemiBold)
                    .foregroundColor(theme.textColor)
                    .frame(width: 30, alignment: .center)

                AsyncImage(url: URL(string: team.imageUrl ?? "")) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } placeholder: {
                    Circle()
                        .fill(theme.secondaryBackground)
                        .overlay(
                            Text(String(team.name.prefix(1)))
                                .font(theme.text14SemiBold)
                                .foregroundColor(theme.textColor)
                        )
                }
                .frame(width: 30, height: 30)
                .clipShape(Circle())

                Text(team.name)
                    .font(theme.text14SemiBold)
                    .foregroundColor(theme.textColor)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .lineLimit(1)

                Spacer()
                
                Image(systemName: "chevron.right")
                    .font(.system(size: 12, weight: .medium))
                    .foregroundColor(theme.textColor.opacity(0.3))
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 16)
            .frame(maxWidth: .infinity)
            .overlay(
                Rectangle()
                    .fill(theme.textColor.opacity(0.1))
                    .frame(height: isLast ? 0 : 0.5)
                    .frame(maxWidth: .infinity)
                    .padding(.horizontal, 16),
                alignment: .bottom
            )
        }
        .buttonStyle(PlainButtonStyle())
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
    }
}

