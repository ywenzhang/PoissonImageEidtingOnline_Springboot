import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import UploadImages from "./components/EditedImages/UploadImages";
import { Provider } from "react-redux";
import store from "./store";
function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/Dashboard" component={Dashboard}></Route>
          <Route exact path="/UploadImages" component={UploadImages}></Route>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
