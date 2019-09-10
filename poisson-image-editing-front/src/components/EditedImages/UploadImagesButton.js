import React from "react";
import { Link } from "react-router-dom";

const UploadImagesButton = () => {
  return (
    <React.Fragment>
      <Link to="/UploadImages" className="btn btn-lg btn-info">
        Upload your Images
      </Link>
    </React.Fragment>
  );
};

export default UploadImagesButton;
