import { Action } from 'redux'

export interface PayloadWithValue {
    value: any;
}

export interface ActionWithPayload<T> extends Action {
    payload: T;
}