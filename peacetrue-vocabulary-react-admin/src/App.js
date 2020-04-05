// in src/App.js
import React from 'react';
import {Admin, fetchUtils, Resource} from 'react-admin';
import {VocabularyList} from './modules/vocabularys/list';
import {VocabularyCreate} from './modules/vocabularys/create';
import {VocabularyEdit} from './modules/vocabularys/edit';
import {VocabularyShow} from './modules/vocabularys/show';
import polyglotI18nProvider from 'ra-i18n-polyglot';
import chineseMessages from 'ra-language-chinese';
import {springDataProvider, springHttpClient} from 'ra-data-spring-rest';

const i18nProvider = polyglotI18nProvider(() => chineseMessages, 'cn');

let httpClient = springHttpClient((url, options = {}) => {
    options.credentials = 'include';
    return fetchUtils.fetchJson(url, options)
        .then(response => {
            let {json} = response;
            if (json.code && json.message) {
                if (json.code !== 'success') {
                    let error = new Error(json.message);
                    error.status = 500;
                    throw error;
                }
                response.json = json.data;
            }
            return response;
        });
});

const dataProvider = springDataProvider('http://localhost:9082', httpClient);
const App = () => (
    <Admin i18nProvider={i18nProvider} dataProvider={dataProvider}>
        <Resource options={{label: '词语'}} name="vocabularys"
                  list={VocabularyList} create={VocabularyCreate}
                  edit={VocabularyEdit} show={VocabularyShow}/>
    </Admin>
);

export default App;