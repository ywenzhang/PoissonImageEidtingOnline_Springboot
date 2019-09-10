import React, { Component } from "react";
import { connect } from "react-redux";
import { fileUploader } from "../../actions/projectActions";
class UploadImages extends Component {
  constructor() {
    super();
    this.background = new File([""], "background");
    this.insertedimage = new File([""], "insertedImages");
    this.mask = new File([""], "mask");
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  onSubmit(e) {
    e.preventDefault();
    this.props.fileUploader(
      this.background,
      this.insertedimage,
      this.mask,
      this.props.history
    );
  }
  render() {
    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">
                Upload your Background Image, Target Image and Mask
              </h5>
              <hr />
              <form>
                <div className="form-group">
                  <input
                    type="file"
                    className="form-control form-control-lg "
                    placeholder="background"
                    name="background"
                  />
                </div>
                <div className="form-group">
                  <input
                    type="file"
                    className="form-control form-control-lg "
                    placeholder="inserted image"
                    name="insertimage"
                  />
                  ceholder="background" />
                </div>
                <div className="form-group">
                  <input
                    type="file"
                    className="form-control form-control-lg "
                    placeholder="mask"
                    name="mask"
                  />
                </div>
                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default connect(
  null,
  { fileUploader }
)(UploadImages);
