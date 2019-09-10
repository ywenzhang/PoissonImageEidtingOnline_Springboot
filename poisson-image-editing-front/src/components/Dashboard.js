import React, { Component } from "react";
import EditedImage from "./EditedImages/EditedImage";
import UploadImagesButton from "./EditedImages/UploadImagesButton";
class Dashboard extends Component {
  render() {
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Poisson Image Editing</h1>
              <br />
              <UploadImagesButton />
              <br />
              <hr />
              <EditedImage />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Dashboard;
