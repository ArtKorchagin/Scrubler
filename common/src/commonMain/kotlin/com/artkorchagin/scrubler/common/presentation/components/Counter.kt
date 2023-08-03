// package com.artkorchagin.scrubler.common.components
//
// import androidx.compose.foundation.layout.Column
// import androidx.compose.material3.Button
// import androidx.compose.material3.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.getValue
// import com.arkivanov.decompose.ComponentContext
// import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
// import com.arkivanov.decompose.value.MutableValue
// import com.arkivanov.decompose.value.Value
// import com.arkivanov.decompose.value.update
// import com.arkivanov.essenty.instancekeeper.InstanceKeeper
// import com.arkivanov.essenty.instancekeeper.getOrCreate
// import com.artkorchagin.scrubler.common.coroutines.coroutineScope
// import kotlinx.coroutines.CoroutineScope
// import kotlinx.coroutines.SupervisorJob
// import kotlinx.coroutines.cancel
// import kotlinx.coroutines.launch
// import kotlinx.coroutines.withContext
// import kotlin.coroutines.CoroutineContext
//
// class Counter(
//     componentContext: ComponentContext,
//     mainContext: CoroutineContext,
//     private val ioContext: CoroutineContext,
// ) : ComponentContext by componentContext {
//
//     // The scope is automatically cancelled when the component is destroyed
//     private val scope = coroutineScope(mainContext + SupervisorJob())
//
//     private val _state = MutableValue(State())
//     val state: Value<State> = _state
//
//    // private var stateS = stateKeeper.consume(key = "SAVED_STATE", clazz = State::class) ?: State()
//     private val someLogic = instanceKeeper.getOrCreate(::SomeLogic)
//
//     init {
//         stateKeeper.register("SAVED_STATE") { stateS }
//     }
//
//     fun foo() {
//         scope.launch {
//             val result = withContext(ioContext) {
//                 "Result" //TODO Some req
//             }
//
//             println(result)
//         }
//     }
//
//     fun increment() {
//         _state.update { it.copy(count = it.count + 1u) }
//     }
//
//
//
//     private class SomeLogic : InstanceKeeper.Instance {
//         override fun onDestroy() {
//             // Clean-up
//         }
//     }
// }
//
// data class State(
//     val count: UInt = 0u
// )
//
//
// @Composable
// fun CounterUi(counter: Counter) {
//     val state by counter.state.subscribeAsState()
//
//     Column {
//         Text(text = state.count.toString())
//         Button(onClick = counter::increment) {
//             Text("Increment")
//         }
//     }
// }
//
// internal class SomeComponent(
//     componentContext: ComponentContext,
//     mainContext: CoroutineContext
// ) : ComponentContext by componentContext {
//
//     private val someRetainedInstance: SomeRetainedInstance =
//         instanceKeeper.getOrCreate { SomeRetainedInstance(mainContext) }
//
//     // init { someRetainedInstance.foo() }
//
// }
//
// internal class SomeRetainedInstance(mainContext: CoroutineContext) : InstanceKeeper.Instance {
//     private val scope = CoroutineScope(mainContext + SupervisorJob())
//
//     fun foo() {
//         scope.launch {
//             //TODO:
//         }
//     }
//
//     override fun onDestroy() {
//         scope.cancel()
//     }
// }
//
