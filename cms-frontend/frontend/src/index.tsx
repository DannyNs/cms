import * as React from 'react'
import * as ReactDOM from 'react-dom'
import { BrowserRouter as Router } from 'react-router-dom'

import { createStore, applyMiddleware, Store } from 'redux'
import { Provider } from 'react-redux'

import thunk from 'redux-thunk'

import AppContainer from './app/app.container'
import rootReducer, { AppStore } from './app/root.reducer'

import './index.css'

const store: Store<AppStore> = createStore(rootReducer, undefined, applyMiddleware(thunk))

ReactDOM.render(
    <Provider store={store}>
        <Router basename="admin">
            <AppContainer />
        </Router>
    </Provider>,
    document.getElementById('root')
)