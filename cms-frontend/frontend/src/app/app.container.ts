import { connect } from 'react-redux'
import { bindActionCreators, Dispatch, ActionCreatorsMapObject } from 'redux'

import { AppStore } from './root.reducer'

import AppComponent, { AppProps, AppFunctions } from './components/app.component'

function mapStateToProps(state: AppStore): AppProps {
    return {

    }
}

function mapDispatchToProps(dispatch: Dispatch<AppStore>): AppFunctions {
    return bindActionCreators({

    }, dispatch)
}

export default connect<AppProps, AppFunctions, undefined>(mapStateToProps, mapDispatchToProps)(AppComponent)