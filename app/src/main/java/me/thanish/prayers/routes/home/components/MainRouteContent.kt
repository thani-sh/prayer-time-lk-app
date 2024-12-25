package me.thanish.prayers.routes.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.thanish.prayers.domain.PrayerTimeTable

@Composable
fun MainRouteContent(times: PrayerTimeTable) {
    val context = LocalContext.current
    val items = times.toList().map {
        Pair(it.type.getLabel(context), it.getTimeString())
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.forEachIndexed { index, (label, value) ->
            val labelStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            val valueStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.5.sp
            )

            if (index != 0) {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .width(180.dp)
                        .alpha(0.1f),
                )
            }

            Row(
                modifier = Modifier
                    .width(180.dp)
                    .padding(vertical = 18.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    textAlign = TextAlign.End,
                    style = labelStyle,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp),
                )
                Text(
                    text = value,
                    textAlign = TextAlign.Start,
                    style = valueStyle,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp),
                )
            }
        }
    }
}
