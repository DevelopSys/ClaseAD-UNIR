import React from 'react';


export default class ErrorBoundary extends React.Component<IErrorBoundaryProps, IErrorBoundaryState> {

  override componentDidCatch(error: any, errorInfo: any) {
    this.setState({
      error,
      errorInfo,
    });
  }

  override render() {
    if (this.state?.error) {
      return (<>
        <h1 className="mb-4">Client error</h1>
        <p>{this.state.error.toString()}</p>
      </>);
    }
    return this.props.children;
  }

}

interface IErrorBoundaryProps {

  readonly children: React.ReactNode;

}

interface IErrorBoundaryState {

  readonly error: any;
  readonly errorInfo: any;

}
