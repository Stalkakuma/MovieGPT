import styles from '../../scss/App.module.scss';

export const Movies = () => {
  return (
    <div className={`text-dark text-center container py-4 px-3 mx-auto ${styles.gradiantBackground}`}>
      <h1>Hello, Bootstrap and Vite!</h1>
      <button class="btn btn-primary" onClick={login}>
        Primary button
      </button>
    </div>
  );
};
