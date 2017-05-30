import * as React from 'react'

export interface AppProps {}
export interface AppChildContext {}
export interface AppFunctions {}

interface Props extends AppProps, AppFunctions {}

export default class AppComponent extends React.Component<Props, undefined> implements React.ChildContextProvider<AppChildContext> {
    static childContextTypes = {

    }
    
    getChildContext(): AppChildContext {
        return {}
    }

    render(): React.ReactElement<Props> {
        return (
            <div>
                <h1>CMS App</h1>
            </div>
        )
    }
}