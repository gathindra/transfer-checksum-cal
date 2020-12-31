import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import utils.ChecksumHelper.calculate


fun main() = Window(
    title = "Transfer Checksum Cal",
    size = IntSize(400, 270)
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        val transferNumber = remember { mutableStateOf(TextFieldValue("")) }
        val transferNumberWithChecksum = remember { mutableStateOf(TextFieldValue("")) }
        val showProgress = remember { mutableStateOf(false) }

        MaterialTheme {
            TextField(
                value = transferNumber.value,
                onValueChange = {
                    transferNumber.value = it
                    if  (transferNumber.value.text.isEmpty()) {
                        transferNumberWithChecksum.value = TextFieldValue("")
                    }
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextField(
                value = transferNumberWithChecksum.value,
                onValueChange = {
                    transferNumberWithChecksum.value = it
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    showProgress.value = true
                    transferNumberWithChecksum.value = TextFieldValue("")
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(500)
                        transferNumberWithChecksum.value = TextFieldValue(calculate(transferNumber.value.text))
                        showProgress.value = false
                    }

                }) {
                Text("Calculate")
            }

            if (showProgress.value) {
                Surface(
                    elevation = 100.dp,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(20.dp, 20.dp),
                        color = Color.Magenta,
                        strokeWidth = 2.dp
                    )
                }
            }
        }

    }


}

