// package com.artkorchagin.scrubler.common.di
//
// import org.koin.core.KoinApplication
// import org.koin.core.annotation.KoinInternalApi
// import org.koin.core.component.KoinComponent
// import org.koin.core.component.KoinScopeComponent
// import org.koin.core.component.createScope
// import org.koin.core.component.getScopeId
// import org.koin.core.component.getScopeName
// import org.koin.core.component.inject
// import org.koin.core.context.startKoin
// import org.koin.core.logger.Level
// import org.koin.core.module.dsl.bind
// import org.koin.core.module.dsl.singleOf
// import org.koin.core.module.dsl.withOptions
// import org.koin.core.qualifier.named
// import org.koin.core.scope.Scope
// import org.koin.dsl.KoinAppDeclaration
// import org.koin.dsl.bind
// import org.koin.dsl.module
//
// interface InterfaceB
// interface InterfaceA
//
// class ClassA(val b: ClassB, val c: ClassC, val string: String) : InterfaceA
// class ClassB() : InterfaceB, KoinComponent{
//     val c by inject<ClassC>()
// }
// class ClassC()
//
// class A : KoinScopeComponent {
//     override val scope: Scope by lazy { createScope(this) }
//
//     fun close() {
//         scope.close()
//     }
//
// }
//
// fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
//     startKoin {
//
//         // properties(
//         //     MR.base.test
//         // )
//
//         appDeclaration()
//         //TODO: properties()
//        //  fileProperties()
//         // getProperty("server_url")
//
//         // logger(PrintLogger(Level.INFO))
//         //
//         // creating custom koin scope
//         // appDeclaration()
//         // modules(
//         //     databaseModule,
//         //     networkModule(enableNetworkLogs),
//         //     dataModule
//         // )
//     }
//
// class MyScope
//
// val networkModule = module {
//
//     scope<MyScope> {
//
//     }
//
//     singleOf(::ClassB) {
//         // named<ClassC>()
//     }
//
//     singleOf(::ClassA) bind InterfaceA::class
//
//     singleOf(::ClassC)
//
//     single {parametersHolder ->
//         // ClassA(parametersHolder.get,)
//     }
//
//
//     includes()
//
// }