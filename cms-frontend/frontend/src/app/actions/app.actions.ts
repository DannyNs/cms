import * as ACTION_TYPES from './index'
import { ActionCreatorsMapObject , Action, ActionCreator } from 'redux'
import { ActionWithPayload, PayloadWithValue } from '../../interfaces'

export const toggleLoader: ActionCreator<Action> = () => {
    return {
        type: ACTION_TYPES.TOGGLE_LOADER
    }
}

export const changePage: ActionCreator<ActionWithPayload<PayloadWithValue>> = (value: PayloadWithValue) => {
    return {
        type: ACTION_TYPES.CHANGE_PAGE,
        payload: value
    }
}