package com.scarry.makakumbha.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.scarry.makakumbha.R
import com.scarry.makakumbha.ui.theme.ColorText
import com.scarry.makakumbha.ui.theme.ComponentShape
import com.scarry.makakumbha.ui.theme.Hightlight
import com.scarry.makakumbha.ui.theme.colorGray
import com.scarry.makakumbha.ui.theme.colorPrimary
import com.scarry.makakumbha.ui.theme.colorSecondary

@Composable
fun NormalTextComponent(
    value: String
){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        , color = colorResource(id = R.color.ColorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(
    value: String
){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.ColorText),
        textAlign = TextAlign.Center
    )
}

@ExperimentalMaterial3Api
@Composable
fun MyTextFieldComponent(labelValue: String, painterResource: Painter) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShape.small),
        label = { Text(text = labelValue) },
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            cursorColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions.Default,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(labelValue: String, painterResource: Painter){
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisual = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShape.small),
        label = { Text(text = labelValue) },
        value = password.value,
        onValueChange = {
            password.value = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            cursorColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisual.value){
                Icons.Filled.Visibility
            } else{
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisual.value){
                stringResource(id = R.string.Hide_Password)
            }else{
                stringResource(id = R.string.Show_Password)
            }
            IconButton(onClick = { passwordVisual.value = !passwordVisual.value}) {
                Icon(imageVector = iconImage , contentDescription = description)
            }
        },
        visualTransformation = if(passwordVisual.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable

fun CheckboxComponent (value: String , onTextSelected : () -> Unit){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        val checkStatus = remember {
            mutableStateOf(false)
        }
        Checkbox(
            checked = checkStatus.value,
            onCheckedChange = {
                checkStatus.value = !checkStatus.value
        } )

        ClickableTextComponent(value = value, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String,onTextSelected : () -> Unit){
    val initialText = "By continuing you accept "
    val privacyPolicyText = "Privacy Policy "
    val andText = "and "
    val termsAndConditionsText = "Terms of use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Hightlight)){
            pushStringAnnotation(
                tag = privacyPolicyText ,
                annotation = privacyPolicyText
            )
            append(privacyPolicyText)
        }
        append(andText)

        withStyle(style = SpanStyle(color = Hightlight)){
            pushStringAnnotation(
                tag = termsAndConditionsText ,
                annotation = termsAndConditionsText
            )
            append(termsAndConditionsText)
        }

    }
    ClickableText(
        text = annotatedString ,
        onClick = {
            onTextSelected()
        }
    )
}

@Composable
fun ButtonComponent(
    value: String,
    onClickAction: () -> Unit
){
    Button(
        onClick = {
            onClickAction()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(colorPrimary, colorSecondary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun DividerTextComponent(){
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorGray,
            thickness = 1.dp
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = stringResource(id = R.string.OR) ,
            fontSize = 18.sp ,
            color = ColorText)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorGray,
            thickness = 1.dp
        )
    }
}
@Composable
fun ClickableLoginTextComponent(onTextSelected : () -> Unit){
    val initialText = "Already have a account? "
    val loginText = "Login"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Hightlight)){
            pushStringAnnotation(
                tag = loginText ,
                annotation = loginText
            )
            append(loginText)
        }

    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString ,
        onClick = {
            onTextSelected()
        }
    )
}

@Composable
fun LoginButtonComponent(
    value: String,
    iconResourceId: Int,
    onClickAction: () -> Unit
) {
    Button(
        onClick = {
            onClickAction()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(colorPrimary, colorSecondary)
                    ),
                    shape = RoundedCornerShape(50.dp)

                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconResourceId),
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp) // Add padding for spacing
            )
            Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ClickableRegisterTextComponent(onTextSelected : () -> Unit){
    val initialText = "Don't have a account? "
    val registerText = "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Hightlight)){
            pushStringAnnotation(
                tag = registerText ,
                annotation = registerText
            )
            append(registerText)
        }

    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString ,
        onClick = {
            onTextSelected()
        }
    )
}

@Composable
fun ImageButtonWithAction(
    imageResId: Int,
    onClickAction: () -> Unit
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onClickAction()
                }
                .size(48.dp)
                .padding(8.dp)
        )
    }
}
@Composable
fun TwoButtonsWithAction(
    firstButtonImageResId: Int,
    firstButtonOnClickAction: () -> Unit,
    secondButtonImageResId: Int,
    secondButtonOnClickAction: () -> Unit,
    spaceBetweenButtons: Dp = 16.dp // Default space between buttons
) {
   Box (
       modifier = Modifier.fillMaxWidth(),
       contentAlignment = Alignment.Center
   ){
       Row(
           modifier = Modifier.padding(horizontal = spaceBetweenButtons)
       ){
           ImageButtonWithAction(
               imageResId = firstButtonImageResId,
               onClickAction = firstButtonOnClickAction
           )
           Spacer(modifier = Modifier.width(spaceBetweenButtons))
           ImageButtonWithAction(
               imageResId = secondButtonImageResId,
               onClickAction = secondButtonOnClickAction
           )
       }
   }
}