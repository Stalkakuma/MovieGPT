import { NavbarComponent as Sidebar } from '../sidebar/NavbarComponent';

export const Layout = ({ children }) => {
  return (
    <div className={`container-fluid g-0 my-md-4`}>
      <div className="row g-0 mx-md-3">
        <div className="col-md-1 col-12 g-0">
          <Sidebar />
        </div>
        <div className="col g-0 ">{children}</div>
      </div>
    </div>
  );
};
