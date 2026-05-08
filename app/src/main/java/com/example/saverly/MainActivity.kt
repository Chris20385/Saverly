package com.example.saverly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaverlyApp()
        }
    }
}

val teal = Color(0xFF0F6F70)

@Composable
fun SaverlyApp() {
    var screen by remember { mutableStateOf("home") }

    when (screen) {
        "home" -> HomeScreen(
            goAdd = { screen = "add" },
            goHistory = { screen = "history" },
            goSettings = { screen = "settings" }
        )

        "add" -> AddExpenseScreen(
            goHome = { screen = "home" },
            goSettings = { screen = "settings" }
        )

        "history" -> HistoryScreen(
            goHome = { screen = "home" },
            goDetails = { screen = "details" },
            goSettings = { screen = "settings" }
        )

        "details" -> ExpenseDetailsScreen(
            goHome = { screen = "home" },
            goSettings = { screen = "settings" }
        )

        "settings" -> SettingsScreen(
            goHome = { screen = "home" },
            goHistory = { screen = "history" },
            goDetails = { screen = "details" },
            goSummary = { screen = "summary" }
        )

        "summary" -> DailySummaryScreen(
            goHome = { screen = "home" },
            goSettings = { screen = "settings" }
        )
    }
}

@Composable
fun HomeScreen(goAdd: () -> Unit, goHistory: () -> Unit, goSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(teal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Todays Spending", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(50.dp))

        WhiteBox("\$50", Modifier.size(190.dp, 110.dp))

        Spacer(modifier = Modifier.height(50.dp))

        WhiteBox("Add Expense", Modifier.fillMaxWidth().height(80.dp), goAdd)

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WhiteBox("Food", Modifier.size(130.dp, 70.dp))
            WhiteBox("Transport", Modifier.size(150.dp, 70.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomNav(goHome = {}, goMiddle = goHistory, goSettings = goSettings)
    }
}

@Composable
fun AddExpenseScreen(goHome: () -> Unit, goSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(teal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add Expense", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(50.dp))

        InputBox("Amount:")
        Spacer(modifier = Modifier.height(35.dp))

        InputBox("Category:")
        Spacer(modifier = Modifier.height(35.dp))

        InputBox("Date:")

        Spacer(modifier = Modifier.weight(1f))

        BottomNav(goHome = goHome, goMiddle = {}, goSettings = goSettings)
    }
}

@Composable
fun HistoryScreen(goHome: () -> Unit, goDetails: () -> Unit, goSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(teal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("History", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(50.dp))

        HistoryItem("Food: \$30", "October 25, 2025", goDetails)
        HistoryItem("Transport: \$20", "October 23, 2025", goDetails)
        HistoryItem("Video Games: \$40", "October 20, 2025", goDetails)

        Spacer(modifier = Modifier.weight(1f))

        BottomNav(goHome = goHome, goMiddle = {}, goSettings = goSettings)
    }
}

@Composable
fun ExpenseDetailsScreen(goHome: () -> Unit, goSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(teal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Expense Details", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(40.dp))

        ExpenseDetail("\$30", "October 25, 2025", "Food")
        Spacer(modifier = Modifier.height(25.dp))

        ExpenseDetail("\$20", "October 23, 2025", "Transportation")
        Spacer(modifier = Modifier.height(25.dp))

        ExpenseDetail("\$40", "October 20, 2025", "Video Games")

        Spacer(modifier = Modifier.weight(1f))

        BottomNav(goHome = goHome, goMiddle = {}, goSettings = goSettings)
    }
}

@Composable
fun SettingsScreen(
    goHome: () -> Unit,
    goHistory: () -> Unit,
    goDetails: () -> Unit,
    goSummary: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(teal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(60.dp))

        WhiteBox("History", Modifier.fillMaxWidth().height(75.dp), goHistory)
        Spacer(modifier = Modifier.height(30.dp))

        WhiteBox("Expense Details", Modifier.fillMaxWidth().height(75.dp), goDetails)
        Spacer(modifier = Modifier.height(30.dp))

        WhiteBox("Daily Summary", Modifier.fillMaxWidth().height(75.dp), goSummary)
        Spacer(modifier = Modifier.height(30.dp))

        WhiteBox("Reset Data", Modifier.fillMaxWidth().height(75.dp))

        Spacer(modifier = Modifier.weight(1f))

        BottomNav(goHome = goHome, goMiddle = {}, goSettings = {})
    }
}

@Composable
fun DailySummaryScreen(goHome: () -> Unit, goSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(teal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Daily Summary", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(90.dp))

        Text("Total Spending", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(60.dp))

        Text("\$50", color = Color.White, fontSize = 44.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(35.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
            WhiteBox("Save", Modifier.size(120.dp, 60.dp))
            WhiteBox("Edit", Modifier.size(120.dp, 60.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomNav(goHome = goHome, goMiddle = {}, goSettings = goSettings)
    }
}

@Composable
fun WhiteBox(text: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Box(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.Black, fontSize = 22.sp)
    }
}

@Composable
fun InputBox(label: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, color = Color.White, fontSize = 24.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
        )
    }
}

@Composable
fun HistoryItem(title: String, date: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Text(title, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(date, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("--------------------------------", color = Color.White, fontSize = 18.sp)
    }
}

@Composable
fun ExpenseDetail(amount: String, date: String, category: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(amount, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("Date: $date", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
        Text("Category: $category", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            WhiteBox("Edit", Modifier.size(115.dp, 55.dp))
            WhiteBox("Delete", Modifier.size(115.dp, 55.dp))
        }
    }
}

@Composable
fun BottomNav(goHome: () -> Unit, goMiddle: () -> Unit, goSettings: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("⌂", color = Color.White, fontSize = 46.sp, modifier = Modifier.clickable { goHome() })
        Text("\$", color = Color.White, fontSize = 46.sp, modifier = Modifier.clickable { goMiddle() })
        Text("⚙", color = Color.White, fontSize = 42.sp, modifier = Modifier.clickable { goSettings() })
    }
}