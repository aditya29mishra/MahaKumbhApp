package com.scarry.makakumbha.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.scarry.makakumbha.R
import com.scarry.makakumbha.components.ButtonComponent
import com.scarry.makakumbha.components.CheckboxComponent
import com.scarry.makakumbha.components.ClickableLoginTextComponent
import com.scarry.makakumbha.components.DividerTextComponent
import com.scarry.makakumbha.components.HeadingTextComponent
import com.scarry.makakumbha.components.MyTextFieldComponent
import com.scarry.makakumbha.components.NormalTextComponent
import com.scarry.makakumbha.components.PasswordTextFieldComponent
import com.scarry.makakumbha.components.TwoButtonsWithAction
import com.scarry.makakumbha.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen (navController: NavController){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)


    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NormalTextComponent(value = stringResource(id = R.string.Hello))
            HeadingTextComponent(value = stringResource(id = R.string.crate_account))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profile)
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.Last_name),
                painterResource = painterResource(id = R.drawable.profile)
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.Email),
                painterResource = painterResource(id = R.drawable.mail)
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.Password),
                painterResource = painterResource(id = R.drawable.outline_lock_24)
            )

            CheckboxComponent(
                value = stringResource(id = R.string.Terms_and_conditons),
                onTextSelected = {
                    navController.navigate(Screen.TermsAndConditionsScreen.route)
                }
            )
            Spacer(modifier = Modifier.heightIn(100.dp))
            ButtonComponent(
                value = stringResource(id = R.string.Register),
                    onClickAction = {
                        navController.navigate(Screen.LoginScreen.route)
                    }
                )
            Spacer(modifier = Modifier.heightIn(20.dp))
            DividerTextComponent()
            TwoButtonsWithAction(
                firstButtonImageResId = R.drawable.google,
                firstButtonOnClickAction = {

                },
                secondButtonImageResId = R.drawable.facebook,
                secondButtonOnClickAction = {

                }
            )
            ClickableLoginTextComponent(onTextSelected = {
                navController.navigate(Screen.AuthTestScreen.route)
            })
        }
    }
}

@Composable
@Preview
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen(navController = rememberNavController())
}