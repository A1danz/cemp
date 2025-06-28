//
//  MatchesView.swift
//  iosApp
//
//  Created by Нияз Ризванов on 20.06.2025.
//

import SwiftUI
import ComposeApp

struct MatchesView: View {
    let component: MatchesComponent
    
    @StateValue
    private var model: MatchesComponentModel
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    
    init(component: MatchesComponent) {
        self.component = component
        _model = StateValue(component.state)
    }
    
    var body: some View {
        ZStack {
            theme.mainBackground
                .ignoresSafeArea()
            
            VStack(spacing: 0) {
                // Header with logout button
                headerView
                
                // Content based on state
                if model.isLoading {
                    loadingView
                } else if model.isError {
                    errorView
                } else if model.matches.isEmpty {
                    emptyStateView
                } else {
                    matchesListView
                }
            }
        }
        .navigationBarHidden(true)
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }
    
    // MARK: - Header View
    private var headerView: some View {
        VStack(spacing: 16) {
            HStack {
                Text(StringRes.feature_matches_title.desc().localized())
                    .font(theme.text28Bold)
                    .foregroundColor(theme.textColor)
                    .fontWeight(.bold)
                
                Spacer()
                
                // Logout button
                Button(action: {
                    component.onIntent(matchesIntent: MatchesComponentIntentOnLogoutClicked())
                }) {
                    Image(systemName: "rectangle.portrait.and.arrow.right")
                        .font(.system(size: 24, weight: .medium))
                        .foregroundColor(theme.textColor)
                }
            }
            .padding(.horizontal, 20)
            
            // Underline
            Rectangle()
                .fill(theme.blueText)
                .frame(height: 2)
                .frame(width: 100)
                .padding(.leading, 20)
                .frame(maxWidth: .infinity, alignment: .leading)
        }
        .padding(.top, 16)
    }
    
    // MARK: - Loading View
    private var loadingView: some View {
        VStack {
            Spacer()
            ProgressView()
                .progressViewStyle(CircularProgressViewStyle(tint: theme.blueText))
                .scaleEffect(1.5)
            
            Text("Loading matches...")
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
                .foregroundColor(theme.textColor.opacity(0.6))
            
            Text(StringRes.feature_matches_error_title.desc().localized())
                .font(theme.text20SemiBold)
                .foregroundColor(theme.textColor)
            
            Text(StringRes.feature_matches_error_message.desc().localized())
                .font(theme.text14Medium)
                .foregroundColor(theme.textColor.opacity(0.7))
                .multilineTextAlignment(.center)
            
            Spacer()
        }
        .padding()
    }
    
    // MARK: - Empty State View
    private var emptyStateView: some View {
        VStack(spacing: 20) {
            Spacer()
            
            Image(systemName: "sportscourt")
                .font(.system(size: 60))
                .foregroundColor(theme.blueText.opacity(0.6))
            
            Text(StringRes.feature_matches_empty_title.desc().localized())
                .font(theme.text24Bold)
                .foregroundColor(theme.textColor)
            
            Text(StringRes.feature_matches_empty_message.desc().localized())
                .font(theme.text16SemiBold)
                .foregroundColor(theme.textColor.opacity(0.7))
                .multilineTextAlignment(.center)
            
            Spacer()
        }
        .padding()
    }
    
    // MARK: - Matches List View
    private var matchesListView: some View {
        ScrollView {
            LazyVStack(spacing: 16) {
                ForEach(model.matches, id: \.id) { match in
                    MatchCardView(match: match) {
                        component.onIntent(matchesIntent: MatchesComponentIntentOnMatchClicked(model: match))
                    }
                    .padding(.horizontal, 20)
                }
            }
            .padding(.vertical, 20)
        }
    }
}

// MARK: - Match Card Component
struct MatchCardView: View {
    let match: MatchModel
    let onTap: () -> Void
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Button(action: onTap) {
            VStack(spacing: 0) {
                // Header with tournament and status
                HStack {
                    Text(match.tournamentName)
                        .font(theme.text14Medium)
                        .foregroundColor(theme.textColor)
                        .lineLimit(1)
                        .truncationMode(.tail)
                    
                    Spacer()
                    
                    Text(match.status.localized())
                        .font(theme.text12Medium)
                        .foregroundColor(theme.textColor.opacity(0.7))
                        .lineLimit(1)
                }
                .padding(.horizontal, 16)
                .padding(.top, 16)
                
                // Teams section
                HStack(spacing: 20) {
                    // Team 1
                    VStack(spacing: 8) {
                        // Team logo
                        teamLogo(imageUrl: match.firstTeam.imageUrl)
                        
                        Text(match.firstTeam.name)
                            .font(theme.text14Medium)
                            .foregroundColor(theme.textColor)
                            .fontWeight(.medium)
                            .lineLimit(1)
                            .truncationMode(.tail)
                        
                        // Score
                        Text("\(match.firstTeam.score)")
                            .font(theme.text16SemiBold)
                            .foregroundColor(theme.blueText)
                            .fontWeight(.bold)
                    }
                    .frame(maxWidth: .infinity)
                    
                    // VS
                    Text(StringRes.feature_matches_vs.desc().localized())
                        .font(theme.text14Medium)
                        .foregroundColor(theme.textColor.opacity(0.7))
                        .lineLimit(1)
                    
                    // Team 2
                    VStack(spacing: 8) {
                        // Team logo
                        teamLogo(imageUrl: match.secondTeam.imageUrl)
                        
                        Text(match.secondTeam.name)
                            .font(theme.text14Medium)
                            .foregroundColor(theme.textColor)
                            .fontWeight(.medium)
                            .lineLimit(1)
                            .truncationMode(.tail)
                        
                        // Score
                        Text("\(match.secondTeam.score)")
                            .font(theme.text16SemiBold)
                            .foregroundColor(theme.blueText)
                            .fontWeight(.bold)
                    }
                    .frame(maxWidth: .infinity)
                }
                .padding(.horizontal, 20)
                .padding(.vertical, 16)
                
                // Date if available
                if let startDate = match.startDate {
                    Text(startDate)
                        .font(theme.text12Medium)
                        .foregroundColor(theme.blueText.opacity(0.8))
                        .lineLimit(1)
                        .padding(.bottom, 16)
                }
            }
            .frame(maxWidth: .infinity)
            .background(theme.secondaryBackground)
            .cornerRadius(12)
            .overlay(
                RoundedRectangle(cornerRadius: 12)
                    .stroke(theme.secondaryBackground.opacity(0.5), lineWidth: 1)
            )
        }
        .buttonStyle(PlainButtonStyle())
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }
    
    // MARK: - Helper Functions
    private func teamLogo(imageUrl: String?) -> some View {
        AsyncImage(url: URL(string: imageUrl ?? "")) { image in
            image
                .resizable()
                .aspectRatio(contentMode: .fit)
        } placeholder: {
            Image(systemName: "shield.fill")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .foregroundColor(theme.blueText)
        }
        .frame(width: 40, height: 40)
    }
}

// Preview temporarily disabled as it requires real component 
