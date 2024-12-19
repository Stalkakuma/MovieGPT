import { SearchComponent } from '../../components/SearchComponent';
import styles from '../../scss/homepage.module.scss';

export const Movies = () => {
  return (
    <div className={`${styles.mainContent}`}>
      <div className={`${styles.homepageStyles}`}>
        <main>
          <SearchComponent />
        </main>
      </div>
    </div>
  );
};
