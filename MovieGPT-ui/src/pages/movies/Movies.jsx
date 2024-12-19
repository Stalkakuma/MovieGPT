import { SearchComponent } from '../../components/SearchComponent';
import styles from '../../scss/homepage.module.scss';

export const Movies = () => {
  return (
    <main className={`${styles.mainContent}`}>
      <div className={`${styles.homepageStyles}`}>
        <SearchComponent />
      </div>
    </main>
  );
};
