package com.cemp.analytics

/**
 * Константы событий для Firebase Analytics
 * 
 * Используйте эти константы вместо строковых литералов для избежания опечаток
 */
object AnalyticsEvents {
    // Экраны
    const val SCREEN_LOGIN = "login_screen"
    const val SCREEN_REGISTER = "register_screen"
    const val SCREEN_MATCHES = "matches_screen"
    const val SCREEN_TEAMS = "teams_screen"
    
    // Пользовательские действия
    const val USER_LOGIN = "user_login"
    const val USER_REGISTER = "user_register"
    const val USER_LOGOUT = "user_logout"
    
    // Матчи
    const val MATCH_VIEW = "match_view"
    const val MATCH_REFRESH = "match_refresh"
    
    // Команды
    const val TEAM_VIEW = "team_view"
    const val TEAM_FILTER = "team_filter"
}

/**
 * Константы параметров для событий
 */
object AnalyticsParams {
    const val USER_ID = "user_id"
    const val MATCH_ID = "match_id"
    const val TEAM_ID = "team_id"
    const val LOGIN_METHOD = "login_method"
    const val ERROR_CODE = "error_code"
    const val FILTER_TYPE = "filter_type"
} 