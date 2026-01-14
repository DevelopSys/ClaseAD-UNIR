const { pathsToModuleNameMapper } = require('ts-jest');
const { compilerOptions } = require('./tsconfig');


module.exports = {
  transform: {
    '.(ts|tsx)$': ['ts-jest', { 'tsconfig': 'tsconfig.json' }]
  },
  testRegex: '.*\\.test\\.tsx$',
  moduleDirectories: ['node_modules', 'src/main/webapp'],
  moduleFileExtensions: ['ts', 'tsx', 'js', 'json'],
  setupFilesAfterEnv: ['<rootDir>/jest.setup.tsx'],
  testEnvironment: 'jsdom'
};
