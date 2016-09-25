/**
 * Created by bondarm on 23.09.16.
 */

const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');

class App extends React.Component {
    render() {
        return (
            <h3>react class</h3>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react'));