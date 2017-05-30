import { combineReducers, Reducer } from 'redux'

import app from './reducers/app.reducer'
import { AppProps } from './components/app.component'

export interface AppStore {
    app: AppProps
}

export default combineReducers<AppStore>({
    app
})