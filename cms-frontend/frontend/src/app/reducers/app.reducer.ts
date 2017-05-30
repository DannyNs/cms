import { Reducer, Action } from 'redux'
import * as ACTION_TYPES from '../actions'
import { ActionWithPayload, PayloadWithValue } from '../../interfaces'
import { AppProps } from '../components/app.component'

const INITIAL_STATE: AppProps = {}

const reducer: Reducer<AppProps> = (state: AppProps = INITIAL_STATE, action: ActionWithPayload<PayloadWithValue>): AppProps => {
    switch (action.type) {
        default:
            return state;
    }
}

export default reducer