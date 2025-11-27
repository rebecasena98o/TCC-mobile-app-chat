package com.example.tccmobile.features.dashboard.data.models

// Modelo de dados para as avaliações dos alunos
data class Review(
    val id: String,
    val name: String,
    val rating: Float, // Ex: 4.8
    val comment: String,
    val date: String = "Hoje"
)

// Modelo de dados para métricas simples (Corrigidos, Pendentes, Concluídos)
data class Metric(
    val title: String,
    val value: String, // O número principal
    val subtext: String, // Texto de descrição ou tendência
    val trend: String, // Ex: +12 este mês
    val isPositive: Boolean, // Se o trend é positivo (cor verde)
    // Opcional: Adicionar ícone
)

// Modelo para dados de qualidade de serviço (Avaliação Média)
data class QualityMetric(
    val title: String,
    val value: String, // Ex: 4.8
    val suffix: String, // Ex: de 5.0 estrelas
    val rating: Float, // 0.0 a 5.0
)

// Modelo para dados de Tempo de Resposta
data class ResponseTimeMetric(
    val title: String,
    val value: String, // Ex: 2.3 dias
    val detail: String, // Ex: Submissão → 1º feedback
    val trend: String, // Ex: -15%
    val isImproving: Boolean, // Se a tendência é de melhora (ex: tempo menor = melhor)
)

// Modelo para Desempenho da Equipe
data class TeamMember(
    val name: String,
    val metricsSummary: String, // Ex: 45 corrigidos • 8 pendentes
    val rating: Float // Avaliação média do membro
)