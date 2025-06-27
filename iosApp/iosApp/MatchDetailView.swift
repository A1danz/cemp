import SwiftUI
import ComposeApp
import Foundation

struct MatchDetailView: View {
    let component: MatchDetailsComponent
    
    @StateValue
    private var model: MatchDetailsComponentModel
    
    @Environment(\.colorScheme) var colorScheme
    @StateObject private var theme = CEMPTheme()
    
    init(component: MatchDetailsComponent) {
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
            
            Text("Загрузка данных матча...")
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
            
            Text("Ошибка загрузки")
                .font(theme.text20SemiBold)
                .foregroundColor(theme.textColor)
            
            Text("Не удалось загрузить информацию о матче")
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
                
                if let match = model.match {
                    tournamentTitleView(match: match)
                    
                    // Match Status
                    matchStatusView(match: match)
                    
                    // Match Teams
                    matchTeamsView(match: match)
                    
                    // Players Section
                    if !model.firstTeamPlayers.isEmpty || !model.secondTeamsPlayers.isEmpty {
                        playersSection
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
                component.onIntent(intent: MatchDetailsComponentIntentBackClicked())
            }) {
                Image(systemName: "arrow.left")
                    .font(.system(size: 20, weight: .medium))
                    .foregroundColor(theme.textColor)
            }
            
            Text("Детали матча")
                .font(theme.text20SemiBold)
                .foregroundColor(theme.textColor)
            
            Spacer()
        }
        .padding(.top, 16)
    }
    
    // MARK: - Tournament Title View
    private func tournamentTitleView(match: MatchModel) -> some View {
        HStack {
            Text(match.tournamentName)
                .font(theme.text24Bold)
                .foregroundColor(theme.textColor)
                .multilineTextAlignment(.leading)
                .lineLimit(nil)
            
            Spacer()
        }
        .padding(.top, 16)
    }
    
    // MARK: - Match Status View
    private func matchStatusView(match: MatchModel) -> some View {
        HStack {
            Text(match.status.localized())
                .font(theme.text16SemiBold)
                .foregroundColor(theme.blueText)
                .lineLimit(1)
            
            Spacer()
            
            if let startDate = match.startDate {
                Text(startDate)
                    .font(theme.text14Medium)
                    .foregroundColor(theme.textColor.opacity(0.7))
            }
        }
    }
    
    // MARK: - Match Teams View
    private func matchTeamsView(match: MatchModel) -> some View {
        HStack(spacing: 32) {
            // First Team - Clickable
            Button(action: {
                component.onIntent(intent: MatchDetailsComponentIntentOnTeamClicked(teamId: Int32(match.firstTeam.id)))
            }) {
                VStack(spacing: 12) {
                    AsyncImage(url: URL(string: match.firstTeam.imageUrl ?? "")) { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                    } placeholder: {
                        Circle()
                            .fill(theme.secondaryBackground)
                            .overlay(
                                Text(String(match.firstTeam.name.prefix(1)))
                                    .font(theme.text20SemiBold)
                                    .foregroundColor(theme.textColor)
                            )
                    }
                    .frame(width: 60, height: 60)
                    .clipShape(Circle())

                    Text(match.firstTeam.name)
                        .font(theme.text16SemiBold)
                        .foregroundColor(theme.textColor)
                        .lineLimit(1)
                        .minimumScaleFactor(0.7)
                        .frame(maxWidth: 80)
                }
            }
            .buttonStyle(PlainButtonStyle())
            
            Spacer()

            // Score
            Text("\(match.firstTeam.score) - \(match.secondTeam.score)")
                .font(theme.text36ExtraBold)
                .foregroundColor(theme.textColor)
                .lineLimit(1)
                .minimumScaleFactor(0.5)

            Spacer()

            // Second Team - Clickable
            Button(action: {
                component.onIntent(intent: MatchDetailsComponentIntentOnTeamClicked(teamId: Int32(match.secondTeam.id)))
            }) {
                VStack(spacing: 12) {
                    AsyncImage(url: URL(string: match.secondTeam.imageUrl ?? "")) { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                    } placeholder: {
                        Circle()
                            .fill(theme.secondaryBackground)
                            .overlay(
                                Text(String(match.secondTeam.name.prefix(1)))
                                    .font(theme.text20SemiBold)
                                    .foregroundColor(theme.textColor)
                            )
                    }
                    .frame(width: 60, height: 60)
                    .clipShape(Circle())

                    Text(match.secondTeam.name)
                        .font(theme.text16SemiBold)
                        .foregroundColor(theme.textColor)
                        .lineLimit(1)
                        .minimumScaleFactor(0.7)
                        .frame(maxWidth: 80)
                }
            }
            .buttonStyle(PlainButtonStyle())
        }
        .padding(.vertical, 24)
    }
    
    // MARK: - Players Section
    private var playersSection: some View {
        VStack(alignment: .leading, spacing: 16) {
            HStack {
                Text("Составы команд")
                    .font(theme.text20SemiBold)
                    .foregroundColor(theme.textColor)
                
                Spacer()
            }
            
            VStack(spacing: 24) {
                // First Team Players
                if !model.firstTeamPlayers.isEmpty {
                    teamPlayersView(
                        teamName: model.match?.firstTeam.name ?? "",
                        players: model.firstTeamPlayers
                    )
                }
                
                // Second Team Players
                if !model.secondTeamsPlayers.isEmpty {
                    teamPlayersView(
                        teamName: model.match?.secondTeam.name ?? "",
                        players: model.secondTeamsPlayers
                    )
                }
            }
        }
    }
    
    // MARK: - Team Players View
    private func teamPlayersView(teamName: String, players: [PlayerModel]) -> some View {
        VStack(alignment: .leading, spacing: 12) {
            Text(teamName)
                .font(theme.text16SemiBold)
                .foregroundColor(theme.blueText)
            
            VStack(spacing: 0) {
                ForEach(Array(players.enumerated()), id: \.offset) { index, player in
                    playerRowView(player: player, isLast: index == players.count - 1)
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
}

#Preview {

} 
