
import SwiftUI
import ComposeApp
import Foundation

struct TeamDetailView: View {
    let component: TeamDetailsComponent
    
    @StateValue
    private var model: TeamDetailsComponentModel
    
    @Environment(\.colorScheme) var colorScheme
    @StateObject private var theme = CEMPTheme()
    
    init(component: TeamDetailsComponent) {
        self.component = component
        _model = StateValue(component.state)
    }
    
    var body: some View {
        ZStack {
            theme.mainBackground
                .ignoresSafeArea()
            
            if model.isLoading {
                loadingView
            } else if model.isError {
                errorView
            } else {
                contentView
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
    
    // MARK: - Loading View
    private var loadingView: some View {
        VStack {
            ProgressView()
                .scaleEffect(1.5)
                .progressViewStyle(CircularProgressViewStyle(tint: theme.blueText))
            
            Text(StringRes.feature_team_detail_loading.desc().localized())
                .font(theme.text16SemiBold)
                .foregroundColor(theme.textColor)
                .padding(.top, 16)
        }
    }
    
    // MARK: - Error View
    private var errorView: some View {
        VStack(spacing: 16) {
            Image(systemName: "exclamationmark.triangle")
                .font(.system(size: 48))
                .foregroundColor(.red)
            
            Text(StringRes.feature_team_detail_error_title.desc().localized())
                .font(theme.text20SemiBold)
                .foregroundColor(theme.textColor)
            
            Text(StringRes.feature_team_detail_error_message.desc().localized())
                .font(theme.text14Medium)
                .foregroundColor(theme.textColor.opacity(0.7))
                .multilineTextAlignment(.center)
        }
        .padding()
    }
    
    // MARK: - Content View
    private var contentView: some View {
        ScrollView {
            VStack(spacing: 24) {
                headerView
                
                if let team = model.team {
                    teamLogoCard(team: team)
                    
                    if !team.roster.isEmpty {
                        rosterSection(team: team)
                    }
                    
                    if !model.recentMatches.isEmpty {
                        recentMatchesSection
                    }
                }
                
                Spacer(minLength: 32)
            }
            .padding(.horizontal, 16)
        }
    }
    
    // MARK: - Header View
    private var headerView: some View {
        HStack {
            Button(action: {
                component.onIntent(intent_: TeamDetailsComponentIntentBackClicked())
            }) {
                Image(systemName: "arrow.left")
                    .font(.system(size: 20, weight: .medium))
                    .foregroundColor(theme.textColor)
            }
            
            Text(StringRes.feature_team_detail_title.desc().localized())
                .font(theme.text20SemiBold)
                .foregroundColor(theme.textColor)
            
            Spacer()
        }
        .padding(.top, 16)
    }
    
    // MARK: - Team Logo Card
    private func teamLogoCard(team: TeamModel) -> some View {
        VStack(spacing: 16) {
            HStack(spacing: 24) {
                AsyncImage(url: URL(string: team.imageUrl ?? "")) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } placeholder: {
                    Circle()
                        .fill(theme.secondaryBackground)
                        .overlay(
                            Text(String(team.name.prefix(1)))
                                .font(theme.text36ExtraBold)
                                .foregroundColor(theme.textColor)
                        )
                }
                .frame(width: 120, height: 120)
                .clipShape(Circle())
                
                Text(team.name)
                    .font(theme.text24Bold)
                    .foregroundColor(theme.textColor)
                    .multilineTextAlignment(.leading)
                
                Spacer()
            }
            .frame(maxWidth: .infinity)
            .padding(20)
            .background(theme.secondaryBackground)
            .cornerRadius(12)
        }
        .padding(.top, 16)
    }
    
    // MARK: - Roster Section
    private func rosterSection(team: TeamModel) -> some View {
        VStack(alignment: .leading, spacing: 16) {
            HStack {
                Text(StringRes.common_roster.desc().localized())
                    .font(theme.text20SemiBold)
                    .foregroundColor(theme.textColor)
                
                Spacer()
            }
            
            VStack(spacing: 0) {
                ForEach(Array(team.roster.enumerated()), id: \.offset) { index, player in
                    playerRowView(player: player, isLast: index == team.roster.count - 1)
                }
            }
            .background(theme.secondaryBackground)
            .cornerRadius(12)
        }
    }
    
    // MARK: - Player Row View
    private func playerRowView(player: PlayerModel, isLast: Bool) -> some View {
        HStack {
            Text(player.name)
                .font(theme.text14SemiBold)
                .foregroundColor(theme.textColor)
                .lineLimit(1)
            
            Spacer()
            
            HStack(spacing: 8) {
                if let nationality = player.nationality {
                    Text(nationality)
                        .font(theme.text12Medium)
                        .foregroundColor(theme.textColor.opacity(0.7))
                        .lineLimit(1)
                }
                
                if let firstName = player.firstName,
                   let lastName = player.secondName {
                    Text("\(firstName) \(lastName)")
                        .font(theme.text12Medium)
                        .foregroundColor(theme.textColor.opacity(0.7))
                        .lineLimit(1)
                }
            }
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 12)
        .overlay(
            Rectangle()
                .fill(theme.textColor.opacity(0.1))
                .frame(height: isLast ? 0 : 0.5)
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 16),
            alignment: .bottom
        )
    }
    
    // MARK: - Recent Matches Section
    private var recentMatchesSection: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text(StringRes.feature_team_recent_matches.desc().localized())
                .font(theme.text20SemiBold)
                .foregroundColor(theme.textColor)
            
            VStack(spacing: 16) {
                ForEach(Array(model.recentMatches.enumerated()), id: \.offset) { index, match in
                    recentMatchCard(match: match)
                }
            }
        }
    }
    
    // MARK: - Recent Match Card
    private func recentMatchCard(match: MatchModel) -> some View {
        Button(action: {
            component.onIntent(intent_: TeamDetailsComponentIntentMatchClicked(match: match))
        }) {
            VStack(spacing: 0) {
                HStack {
                    Text(match.tournamentName)
                        .font(theme.text14SemiBold)
                        .foregroundColor(theme.textColor)
                        .lineLimit(1)
                    
                    Spacer()
                    
                    Text(match.status.localized())
                        .font(theme.text12Medium)
                        .foregroundColor(theme.blueText)
                        .lineLimit(1)
                }
                .padding(.horizontal, 16)
                .padding(.top, 16)
                
                HStack(spacing: 24) {
                    VStack(spacing: 8) {
                        AsyncImage(url: URL(string: match.firstTeam.imageUrl ?? "")) { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                        } placeholder: {
                            Circle()
                                .fill(theme.secondaryBackground)
                                .overlay(
                                    Text(String(match.firstTeam.name.prefix(1)))
                                        .font(theme.text12SemiBold)
                                        .foregroundColor(theme.textColor)
                                )
                        }
                        .frame(width: 30, height: 30)
                        .clipShape(Circle())
                        
                        Text(match.firstTeam.name)
                            .font(theme.text12Medium)
                            .foregroundColor(theme.textColor)
                            .lineLimit(1)
                    }
                    .frame(maxWidth: .infinity)
                    
                    Text("\(match.firstTeam.score) - \(match.secondTeam.score)")
                        .font(theme.text14SemiBold)
                        .foregroundColor(theme.textColor)
                        .lineLimit(1)
                    
                    VStack(spacing: 8) {
                        AsyncImage(url: URL(string: match.secondTeam.imageUrl ?? "")) { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                        } placeholder: {
                            Circle()
                                .fill(theme.secondaryBackground)
                                .overlay(
                                    Text(String(match.secondTeam.name.prefix(1)))
                                        .font(theme.text12SemiBold)
                                        .foregroundColor(theme.textColor)
                                )
                        }
                        .frame(width: 30, height: 30)
                        .clipShape(Circle())
                        
                        Text(match.secondTeam.name)
                            .font(theme.text12Medium)
                            .foregroundColor(theme.textColor)
                            .lineLimit(1)
                    }
                    .frame(maxWidth: .infinity)
                }
                .padding(.horizontal, 24)
                .padding(.vertical, 12)
                
                if let startDate = match.startDate {
                    Text(startDate)
                        .font(theme.text12Medium)
                        .foregroundColor(theme.textColor.opacity(0.7))
                        .lineLimit(1)
                        .padding(.bottom, 16)
                }
            }
            .frame(maxWidth: .infinity)
            .background(theme.secondaryBackground)
            .cornerRadius(12)
        }
        .buttonStyle(PlainButtonStyle())
    }
}

